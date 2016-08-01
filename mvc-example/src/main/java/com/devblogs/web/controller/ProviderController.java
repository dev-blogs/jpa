package com.devblogs.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.devblogs.model.Provider;
import com.devblogs.service.ProviderService;

@RequestMapping("/providers")
@Controller
public class ProviderController {
	final Logger logger = LoggerFactory.getLogger(ProviderController.class);
	
	@Autowired
	private ProviderService providerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing providers");
		
		List<Provider> contacts = providerService.findAll();
		uiModel.addAttribute("providers", contacts);
		
		logger.info("No. of providers: " + contacts.size());
		
		return "providers/list";
	}
}