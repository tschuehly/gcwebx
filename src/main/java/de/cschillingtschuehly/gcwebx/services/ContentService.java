package de.cschillingtschuehly.gcwebx.services;
import de.cschillingtschuehly.gcwebx.helpers.CRUDService;
import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import org.springframework.stereotype.Service;

@Service
public class ContentService extends CRUDService<Content, ContentRepository> {
}
