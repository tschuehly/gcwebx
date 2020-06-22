package de.cschillingtschuehly.gcwebx.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.services.ContentService;
import de.cschillingtschuehly.gcwebx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class ContentController {
    @Autowired
    private ContentService contentService;


    //@CrossOrigin(origins="http://localhost:4200/getContent",allowedHeaders = "*")
    @PostMapping(value = "/api/getContentByID",produces = {"application/json"})
    public ResponseEntity getContentById(@RequestBody Integer id){
        Content content = contentService.getContentById(id);
        System.out.println(content);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/api/getContent",produces = {"application/json"})
    public ResponseEntity getContent(){
        List<Content> contentList = contentService.getContent();
        System.out.println(contentList);
        return ResponseEntity.ok().body(contentList);
    }
    @PutMapping(value="/api/updateContent",produces = {"application/json"})
    public ResponseEntity editContent(@RequestBody Content content) throws JsonProcessingException {
        System.out.println(content);
        Content updatedContent = contentService.updateContent(content);
        System.out.println(updatedContent);
        return ResponseEntity.ok().body(updatedContent);
    }
    @PostMapping(value="/api/createContent",produces = {"application/json"})
    public ResponseEntity createContent(@RequestBody Content content) throws JsonProcessingException {
        System.out.println(content);
        Content createdContent = contentService.createContent(content);
        return ResponseEntity.ok().body(createdContent);
    }
    @DeleteMapping(value="/api/deleteContent/{contentId}",produces = {"application/json"})
    public ResponseEntity deleteContent(@PathVariable Integer contentId) throws JsonProcessingException {
        contentService.deleteContent(contentId);
        return ResponseEntity.ok().body("{\"contentDeleted\":\"true\"}");
    }

}
