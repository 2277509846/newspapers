package com.example.newspapers.service.impl;

import com.example.newspapers.dao.NewspaperDao;
import com.example.newspapers.entity.Newspaper;
import com.example.newspapers.service.NewspaperService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewspaperServiceImpl implements NewspaperService {
    @Resource
    NewspaperDao newspaperDao;

    @Override
    public Map<String, Object> newspaperList(String adminUsername, String condition, Integer page, Integer pageCount) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer total = newspaperDao.total2("%" + condition + "%", adminUsername);
        result.put("total", total);
        if (page * pageCount > total) page = 0;
        List<Newspaper> newspapers = newspaperDao.newspaperList(adminUsername, "%" + condition + "%", page * pageCount, pageCount);
        result.put("newspapers", newspapers);
        return result;
    }

    @Override
    public Map<String, Object> newspaperList2(String condition, Integer page, Integer pageCount) {
        Map<String, Object> result = new HashMap<String, Object>();
        Integer total = newspaperDao.total("%" + condition + "%");
        result.put("total", total);
        if (page * pageCount > total) page = 0;
        List<Newspaper> newspapers = newspaperDao.newspaperList2("%" + condition + "%", page * pageCount, pageCount);
        result.put("newspapers", newspapers);
        return result;
    }

    @Override
    public String addNewspaper(Newspaper newspaper, MultipartFile image) throws IOException {
        saveImage(newspaper, image);
        newspaperDao.addNewspaper(newspaper);
        return "添加成功";
    }

    @Override
    public String updateNewspaper(Newspaper newspaper, MultipartFile image) throws IOException {
        saveImage(newspaper, image);
        newspaperDao.updateNewspaper(newspaper);
        return "修改成功";
    }

    private void saveImage(Newspaper newspaper, MultipartFile image) throws IOException {
        if (image == null) return;
        String imageName = image.getOriginalFilename();
        String fileName = imageName.substring(0, imageName.lastIndexOf("."));
        String suffix = imageName.substring(imageName.lastIndexOf("."));
        image.transferTo(new File("E://upload/uploads/" + fileName.hashCode() + suffix));
        newspaper.setImage_url("http://127.0.0.1:8080/uploads/" + fileName.hashCode() + suffix);
    }

    @Override
    public String deleteNewspaper(Integer id) {
        if (newspaperDao.findNewspaperById(id) == null) return "编号不存在";
        newspaperDao.deleteNewspaper(id);
        return "删除成功";
    }
}
