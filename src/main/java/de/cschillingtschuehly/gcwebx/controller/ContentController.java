package de.cschillingtschuehly.gcwebx.controller;

import de.cschillingtschuehly.gcwebx.helpers.RESTController;
import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import de.cschillingtschuehly.gcwebx.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/api/content")
public class ContentController extends RESTController<Content, ContentRepository,ContentService> {
    @Autowired
    private ContentService contentService;
}
