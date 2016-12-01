package com.springapp.mvc;

import com.springapp.dao.BurberryShoeDAO;
import com.springapp.model.BurberryShoe;
import com.springapp.model.EPC;
import com.springapp.model.UnassociatedTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by michaelgoode on 29/11/2016.
 */

@Controller
@RequestMapping
public class BurberryController {

    @Autowired
    private BurberryShoeDAO burberryShoeDAO;

    @RequestMapping(value = "/burberry/index", method=RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("burberry/index");
    }

    @RequestMapping(value = "/burberry/addshoe", method=RequestMethod.GET)
    public ModelAndView showform() {
        return new ModelAndView("burberry/addshoe","command", new BurberryShoe());
    }

    @RequestMapping(value = "/burberry/saveshoe", method = RequestMethod.GET)
    public ModelAndView addShoe(@ModelAttribute("shoe") BurberryShoe burberryShoe) {
        burberryShoeDAO.saveShoe(burberryShoe.getImagecode(), burberryShoe.getImagename());
        return new ModelAndView("burberry/index");
    }

    @RequestMapping(value = "/burberry/findshoe", method = RequestMethod.GET)
    public ModelAndView getShoe(@ModelAttribute("shoe") BurberryShoe burberryShoe) {
        List<String> list = burberryShoeDAO.findShoeReferences(burberryShoe.getImagecode());
        ModelAndView modelAndView = new ModelAndView("burberry/findshoe","command",new BurberryShoe());
        modelAndView.addObject("list", list);
        modelAndView.addObject("shoe", burberryShoe);
        return modelAndView;
    }

    @RequestMapping(value = "/burberry/file", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
        final String folderName = "Burberry";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + folderName);
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
                    HashSet<BurberryShoe> items = processBurberryShoeFile(serverFile.getAbsolutePath());
                    burberryShoeDAO.postBatch(items, file.getOriginalFilename());
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

    public HashSet<BurberryShoe> processBurberryShoeFile( String path ) {
        HashSet<BurberryShoe> items = new HashSet<BurberryShoe>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            BurberryShoe shoe = null;
            br = new BufferedReader(new FileReader(path));
            String top = br.readLine();
            while (((line = br.readLine()) != null) && (!top.isEmpty())) {
                // use comma as separator
                String[] cells = line.split(cvsSplitBy);
                if (!cells[0].trim().equals("")) {
                    shoe = new BurberryShoe();
                    shoe.setImagecode(cells[0].trim().toUpperCase());
                    shoe.setImagename(cells[1].trim().toUpperCase());
                    items.add(shoe);
                }
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
                    return items;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
    }
}
