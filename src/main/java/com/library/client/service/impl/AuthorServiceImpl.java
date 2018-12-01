/**
 * 
 */
package com.library.client.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.client.model.Author;
import com.library.client.repository.AuthorRepository;
import com.library.client.service.AuthorService;

/**
 * @author ANIZAM
 *
 */

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public List<Author> findAll() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}	
	
}
