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
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MapperService mapperService;

    public String getContentTable(){
        ObjectMapper mapper = mapperService.jacksonMapper();
        List<Content> contentList = contentRepository.findAll();
        ArrayNode contentTable = mapper.valueToTree(contentList);
        return contentTable.toString();
    }
    public String updateContent(Content pcontent) throws JsonProcessingException {
        ObjectMapper mapper = mapperService.jacksonMapper();
        Content content = contentRepository.findById(pcontent.getId()).get();
        contentRepository.save(content);
        return mapper.writeValueAsString(content);
    }

    public String createContent(Content pcontent) throws JsonProcessingException {
        ObjectMapper mapper = mapperService.jacksonMapper();
        contentRepository.save(pcontent);
        Content content = contentRepository.findById(pcontent.getId()).get();
        System.out.println(content.toString());
        return mapper.writeValueAsString(content);
    }
    public void deleteContent(Content pContent){
        contentRepository.delete(pContent);

    }


}
