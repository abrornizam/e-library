/**
 * 
 */
package com.library.demo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.demo.model.Denda;
import com.library.demo.repository.DendaRepository;
import com.library.demo.service.DendaService;

/**
 * @author ANIZAM
 *
 */

@Transactional
@Service
public class DendaServiceImpl implements DendaService {

	@Autowired
	private DendaRepository dendaRepository;
	
	@Override
	public Denda saveDenda(Denda denda) {
		// TODO Auto-generated method stub
		int totalData = dendaRepository.getTotalData();
		int seqId = 0;	
		if(totalData == 0) {
			seqId = 1;
		}else {
			seqId = dendaRepository.getSequenceId()+1;
		}
    	denda.setId(seqId);
    	String kddenda = Integer.valueOf(seqId).toString();
    	denda.setkddenda("CHR".concat(kddenda));
    	denda.setStatus(true);
		return dendaRepository.save(denda);
	}
	
}
