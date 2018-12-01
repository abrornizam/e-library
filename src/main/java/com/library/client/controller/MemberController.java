package com.library.client.controller;

/**
 * @author ANIZAM
 *
 */

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.client.model.Member;
import com.library.client.service.MemberService;

@Controller
@RequestMapping("/library/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/listMember", method = RequestMethod.GET)
	public String listMember(ModelMap model) {
		List<Member> member = memberService.findAll();
		model.addAttribute("member", member);
		return "/library/member/listMember";
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.GET)
	public String addMember() {
		return "/library/member/addMember";
	}

	@RequestMapping(value = "/saveMember", method = RequestMethod.POST)
	public String saveMember(@Valid Member member, BindingResult result, ModelMap model) {
		memberService.saveMember(member);		
		return "redirect:/library/member/listMember";
	}
	
	@RequestMapping(value = "/detail/{idmember}", method = RequestMethod.GET)
	public String detailMember(@PathVariable String idmember, ModelMap model) {
		Member member = memberService.findByIdmember(idmember);
		model.addAttribute("member", member);
		return "/library/member/detailMember";
	}
	
	@RequestMapping(value = "/edit/{idmember}", method = RequestMethod.GET)
	public String editMember(@PathVariable String idmember, ModelMap model) {
		Member member = memberService.findByIdmember(idmember);
		model.addAttribute("member", member);
		return "/library/member/editMember";
	}
	
	@RequestMapping(value = "/saveEditMember", method = RequestMethod.POST)
	public String saveEditMember(@Valid Member member, BindingResult result, ModelMap model) {
		memberService.editMember(member);
		return "redirect:/library/member/listMember";
	}
	
	@RequestMapping(value = "/delete/{idmember}", method = RequestMethod.GET)
	public String deleteMember(@PathVariable String idmember) {
		memberService.deleteMember(idmember);
		return "redirect:/library/member/listMember";
	}

}
