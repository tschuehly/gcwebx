package de.cschillingtschuehly.gcwebx.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MapperService mapperService;

    public Content getContentById(int id){
        Content content = contentRepository.findById(id).get();
        return content;
    }

    public List<Content> getContent(){
        List<Content> contentList = contentRepository.findAll();
        System.out.println(contentList);
        return contentList;
    }

    public Content updateContent(Content pcontent) throws JsonProcessingException {
        contentRepository.save(pcontent);
        return pcontent;
    }

    public Content createContent(Content content) throws JsonProcessingException {
        contentRepository.save(content);
        Content savedcontent = contentRepository.findById(content.getId()).get();
        System.out.println(savedcontent.toString());
        return savedcontent;
    }
    public void deleteContent(Integer contentId){
        contentRepository.deleteById(contentId);
        System.out.println("deleted element" + contentId);

    }


}
