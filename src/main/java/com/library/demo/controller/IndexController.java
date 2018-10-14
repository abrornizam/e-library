package com.library.demo.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.library.demo.model.Buku;
import com.library.demo.service.BukuService;

/**
 * @author ANIZAM
 *
 */

@Controller
public class IndexController extends ResponseEntityExceptionHandler {

	@Autowired
	private BukuService bukuService;
	
	@GetMapping(value = "/perpus/index")
	public String indexApp(ModelMap model) {
		List<Buku> listBuku = bukuService.findAll();
		model.addAttribute("buku", listBuku);
		return "/perpus/buku/listBuku";
	}
		
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status != null) {
			int statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "perpus/error-404";
			}else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "perpus/error-500";
			}else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
				return "perpus/error-400";
			}
		}
		return "error";
	}
	
}
