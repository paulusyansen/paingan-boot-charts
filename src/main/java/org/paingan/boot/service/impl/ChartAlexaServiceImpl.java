package org.paingan.boot.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.paingan.boot.domain.ChartAlexa;
import org.paingan.boot.repository.ChartAlexaRepository;
import org.paingan.boot.repository.spec.BaseSpecificationBuilder;
import org.paingan.boot.service.ChartAlexaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ChartAlexaServiceImpl implements ChartAlexaService {

	@Autowired
	private ChartAlexaRepository chartAlexaRepository;
	
	/*
	 * (non-Javadoc)
	 * @see org.paingan.boot.service.ChartAlexaService#search(java.lang.String)
	 */
	public List<ChartAlexa> search(String search) {
		BaseSpecificationBuilder<ChartAlexa> builder = new BaseSpecificationBuilder<ChartAlexa>();
		
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        
        while (matcher.find()) {
        	//SearchOperation sOpr = SearchOperation.getSimpleOperation(matcher.group(2).charAt(0));
            builder.with(matcher.group(1), matcher.group(2) , matcher.group(3), null, null);
        }
         
        Specification<ChartAlexa> spec = builder.build();
        
        return chartAlexaRepository.findAll(spec);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.paingan.boot.service.ChartAlexaService#findAll()
	 */
	public List<ChartAlexa> findAll() {
        return chartAlexaRepository.findAll();
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see org.paingan.boot.service.ChartAlexaService#save(org.paingan.boot.domain.ChartAlexa)
	 */
	public ChartAlexa save(ChartAlexa chartAlexa) {
		return chartAlexaRepository.save(chartAlexa);
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.paingan.boot.service.ChartAlexaService#findAlexaByid(Long)
	 */
	@Override
	public ChartAlexa findAlexaByid(Long id) {
		return chartAlexaRepository.getOne(id);
	}
		
}
