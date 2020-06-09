package de.cschillingtschuehly.gcwebx.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.services.ContentService;
import de.cschillingtschuehly.gcwebx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class ContentController {
    @Autowired
    private ContentService contentService;


    //@CrossOrigin(origins="http://localhost:4200/getContent",allowedHeaders = "*")
    @GetMapping(value = "/getContent",produces = {"application/json"})
    public ResponseEntity getContent(){
        String contentTable = contentService.getContentTable();
        return ResponseEntity.ok().body(contentTable);
    }
    @PutMapping(value="/updateContent",produces = {"application/json"})
    public ResponseEntity editContent(@RequestBody Content content) throws JsonProcessingException {
        System.out.println(content);
        String updatedContent = contentService.updateContent(content);
        return ResponseEntity.ok().body(updatedContent);
    }
    @PostMapping(value="/createContent",produces = {"application/json"})
    public ResponseEntity createContent(@RequestBody Content content) throws JsonProcessingException {
        System.out.println(content);
        String createdContent = contentService.createContent(content);
        return ResponseEntity.ok().body(createdContent);
    }
    @DeleteMapping(value="/deleteContent",produces = {"application/json"})
    public ResponseEntity deleteContent(@RequestBody Content content) throws JsonProcessingException {
        contentService.deleteContent(content);
        return ResponseEntity.ok().body("{\"contentDeleted\":\"true\"}");
    }

}
