package com.springapp.mvc;

/**
 * Created by michaelgoode on 06/08/2016.
 */
import com.springapp.dao.*;
import com.springapp.model.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.*;

@Controller
@RequestMapping
public class AssociationController {

    @Autowired
    private HeaderDAO headerDAO;

    @Autowired
    private EPCDAO epcDAO;

    @Autowired
    private AssociationBatchDAO associationBatchDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private UnassociatedReportDAO unassociatedReportDAO;

    @RequestMapping(value="/batch")
    public ModelAndView listBatch(ModelAndView model) throws IOException {
        List<AssociationBatch> list = associationBatchDAO.listBatch();
        model.addObject("list", list);
        model.setViewName("batch");
        return model;
    }

    @RequestMapping(value="/list")
    public ModelAndView listContact(ModelAndView model) throws IOException {
        List<Header> list = headerDAO.list();
        model.addObject("list", list);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/searchepc", method = RequestMethod.GET)
    public ModelAndView searchepc() {
        return new ModelAndView("searchepc", "command", new EPC());
    }

    @RequestMapping(value = "/checkepc", method = RequestMethod.GET)
    public ModelAndView checkepc() {
        return new ModelAndView("checkepc", "command", new EPC());
    }

    @RequestMapping(value="/checkepcresult", method = RequestMethod.POST)
    public String validateEPC(EPC aEPC, Model model) throws IOException {
        EPC newEPC = epcDAO.checkEPC(aEPC.getEpc());
        model.addAttribute("epcNo", aEPC.getEpc());
        model.addAttribute("valuesMap", newEPC.getValuesMap());
        model.addAttribute("messages", newEPC.getMessages());
        return "checkepcresult";
    }

    @RequestMapping(value = "/searchcontract", method = RequestMethod.GET)
    public ModelAndView searchcontract() {
        return new ModelAndView("searchcontract", "command", new Contract());
    }

    @RequestMapping(value="/uploadreport", method = RequestMethod.GET)
    public ModelAndView uploadreport(Model model) {
        return new ModelAndView("uploadreport", "command", new Report());
    }

    @RequestMapping(value="/epcresults", method = RequestMethod.POST)
    public String findEPC(EPC aEPC, Model model) throws IOException {
        List<EPC> list = epcDAO.searchEPC(aEPC.getEpc());
        model.addAttribute("list", list);
        return "epcresults";
    }

    @RequestMapping(value="/contractresults", method = RequestMethod.POST)
    public String findContract(Contract aContract, Model model) throws IOException {
        List<Contract> list = contractDAO.searchContract(aContract.getContract());
        model.addAttribute("list", list);
        return "contractresults";
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    String uploadFileHandler(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + "MS_Association_Reports");
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                String[] names = dir.list();
                List<String> lstFiles = Arrays.asList(names);
                if (!lstFiles.contains(file.getOriginalFilename())) {
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + file.getOriginalFilename());
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                    HashSet<UnassociatedTag> tags = processUnassociatedReport(serverFile.getAbsolutePath());
                    unassociatedReportDAO.postBatch(tags, file.getOriginalFilename());
                    return "index";
                } else return "You failed to upload " + file.getOriginalFilename() + " => previously uploaded";

            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getOriginalFilename()
                    + " because the file was empty.";
        }
    }

    public HashSet<UnassociatedTag> processUnassociatedReport( String path ) {
        HashSet<UnassociatedTag> tags = new HashSet<UnassociatedTag>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            UnassociatedTag tag = null;
            br = new BufferedReader(new FileReader(path));
            String top = br.readLine();
            while (((line = br.readLine()) != null) && (!top.isEmpty())) {
                // use comma as separator
                String[] cells = line.split(cvsSplitBy);
                tag = new UnassociatedTag();
                tag.setEPC(cells[5].trim());
                EPC newEPC = epcDAO.checkEPC(tag.getEPC());
                tag.setValid(newEPC.isSmlValid());
                tags.add(tag);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                    return tags;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
    }


    @RequestMapping(value="/unassociated")
    public ModelAndView listUnassociated(ModelAndView model) throws IOException {
        List<UnassociatedTag> list = unassociatedReportDAO.getReport();
        model.addObject("list", list);
        model.setViewName("unassociated");
        return model;
    }
}
