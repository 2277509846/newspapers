package com.example.newspapers.service;

import com.example.newspapers.entity.Newspaper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface NewspaperService {
    Map<String, Object> newspaperList(String adminUsername, String condition, Integer page, Integer pageCount);
    Map<String, Object> newspaperList2(String condition, Integer page, Integer pageCount);
    String addNewspaper(Newspaper newspaper, MultipartFile image) throws IOException;
    String updateNewspaper(Newspaper newspaper, MultipartFile image) throws IOException;
    String deleteNewspaper(Integer id);
}
