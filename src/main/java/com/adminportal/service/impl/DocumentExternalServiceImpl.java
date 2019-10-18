/*  Company Name  : Spoken Tutorial IIT bombay
 * 	Author Name	  : Om Prakash
 * 	Version		  : 1.0
 */

package com.adminportal.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.content.ArticleExternal;
import com.adminportal.content.DocumentExternal;
import com.adminportal.content.Topic;
import com.adminportal.domain.User;
import com.adminportal.repository.DocumentExternalRepository;
import com.adminportal.service.DocumentExternalService;

@Service
public class DocumentExternalServiceImpl implements DocumentExternalService{
	
	@Autowired
	private DocumentExternalRepository documentRepo;

	@Override
	public List<DocumentExternal> findAll() {
		
		return documentRepo.findAllBytype("Document");
	}

	@Override
	public void deleteDocumentById(int id) {
		Optional<DocumentExternal> local=documentRepo.findById(id);
		DocumentExternal temp=local.get();
		documentRepo.deleteById(temp.getDocumentId());
		
	}

	@Override
	public DocumentExternal findByid(int id) {
		Optional<DocumentExternal> local=documentRepo.findById(id);
		return local.get();
	}

	@Override
	@Transactional
	public boolean updateDocument(String desc, String source, String url, Timestamp date, int Id) {
		int temp=documentRepo.updateDocument(desc, source, url, date, Id);
		
		if(temp>0) {
			return true;
		}else {
			return false;
		}
			
		
		
	}

	@Override
	public int countRow() {
		
		return (int) documentRepo.count();
	}

	@Override
	@Transactional
	public boolean EnableDocumentContent(int status, int id) {
		
		int status1=documentRepo.EnableDocumentContent(status, id);
		if(status1>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public List<DocumentExternal> findAllByTopic(Topic topic) {
		
		List<DocumentExternal> localDocument=documentRepo.findAllBytopicAndType(topic, "Document");
		
		return localDocument;
	}

	@Override
	public List<DocumentExternal> findALlByUser(User usr) {
		
		return documentRepo.findAllByuser(usr,"Document");
	}

}
