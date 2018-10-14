package com.badrconsulting.jobinterview.controller;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.badrconsulting.jobinterview.controller.util.Pager;
import com.badrconsulting.jobinterview.repository.DomainRepository;
import com.badrconsulting.jobinterview.service.QRService;
import com.badrconsulting.jobinterview.service.dto.QRDTO;

@Controller("/qrs")
class QRController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};
    
    
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "qrs/createOrUpdateQuestionForm";
    private final  QRService qRService;
    private final  DomainRepository domainRepository;

    public QRController(QRService qRService,DomainRepository domainRepository) {
    	this.domainRepository = domainRepository;
        this.qRService = qRService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types")
    public Collection<Long> populatePetTypes() {
        return this.domainRepository.findAll().stream().map(domain -> domain.getId()).collect( Collectors.toList());
    }
    
    @InitBinder("QRDTO")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping("/qrs/new")
    public String initCreationForm(Map<String, Object> model) {
    	QRDTO qrDTO = new QRDTO();
        model.put("QRDTO", qrDTO);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/qrs/new")
    public String processCreationForm(@Valid QRDTO qrDTO, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
        	qrDTO = this.qRService.save(qrDTO);
            return "redirect:/qrs/" + qrDTO.getId();
        }
    }

    @GetMapping("/qrs/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("QRDTO", new QRDTO());
        return "qrs/findQuestions";
    }

    @GetMapping("/qrs")
    public String processFindForm(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page, QRDTO qrDTO, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /qrs to return all records
        if (qrDTO.getQuestion() == null) {
            qrDTO.setQuestion(""); // empty string signifies broadest possible search
        }

        // find qRService by last name
        
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<QRDTO> results = this.qRService.searchByDomaineNameForLanguage("java","en", PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(results.getTotalPages(), results.getNumber(), BUTTONS_TO_SHOW);

        if (results.getSize()==0) {
            // no qRService found
            result.rejectValue("question", "notFound", "not found");
            model.put("QRDTO", new QRDTO());
            return "qrs/findQuestions";
        } else if (results.getSize() == 1) {
            // 1 qrDTO found
            qrDTO = results.iterator().next();
            return "redirect:/qrs/" + qrDTO.getId();
        } else {
            // multiple qRService found
        	model.put("selections", results);
        	model.put("selectedPageSize", evalPageSize);
        	model.put("pageSizes", PAGE_SIZES);
        	model.put("pager", pager);

            return "qrs/questionsList";
        }
    }

    @GetMapping("/qrs/{qrDTOId}/edit")
    public String initUpdateQRDTOForm(@PathVariable("qrDTOId") long qrDTOId, Model model) {
        QRDTO qrDTO = this.qRService.findOne(qrDTOId);
        model.addAttribute(qrDTO);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/qrs/{qrDTOId}/edit")
    public String processUpdateQRDTOForm(@Valid QRDTO qrDTO, BindingResult result, @PathVariable("qrDTOId") long qrDTOId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            qrDTO.setId(qrDTOId);
            this.qRService.save(qrDTO);
            return "redirect:/qrs/{qrDTOId}";
        }
    }

    /**
     * Custom handler for displaying an qrDTO.
     *
     * @param qrDTOId the ID of the qrDTO to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/qrs/{qrDTOId}")
    public ModelAndView showQRDTO(@PathVariable("qrDTOId") long qrDTOId) {
        ModelAndView mav = new ModelAndView("qrs/questionDetails");
        mav.addObject(this.qRService.findOne(qrDTOId));
        return mav;
    }

}
