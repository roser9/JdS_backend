package com.example.poema.copy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoemaServiceCopy {
	@Autowired
	private PoemaRepositoryCopy poemaRepository;

	public List<PoemaCopy> getAll() {
		return poemaRepository.findAll();
	}

	public List<PoemaCopy> findByAuthor(String name) {
		return poemaRepository.findByAuthor(name);
	}	
	
	public List<PoemaCopy> findByDate(String date_ini, String date_fi) {
		return poemaRepository.findByDate(date_ini, date_fi);
	}
}
