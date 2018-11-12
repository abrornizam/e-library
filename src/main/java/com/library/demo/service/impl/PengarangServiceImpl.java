/**
 * 
 */
package com.library.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.demo.model.Pengarang;
import com.library.demo.repository.PengarangRepository;
import com.library.demo.service.PengarangService;

/**
 * @author ANIZAM
 *
 */

@Service
@Transactional
public class PengarangServiceImpl implements PengarangService {

	@Autowired
	PengarangRepository pengarangRepository;
	
	@Override
	public List<Pengarang> findAll() {
		// TODO Auto-generated method stub
		return pengarangRepository.findAll();
	}	
	
}
