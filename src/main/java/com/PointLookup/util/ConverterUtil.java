package com.PointLookup.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ConverterUtil<T,Y> {
	
	private ModelMapper modelMapper;
	
	private Class<T> typeClassT;
	
	private Class<Y> typeClassY;
	
	public ConverterUtil() {}
	
	public ConverterUtil(Class<T> typeClassT,Class<Y> typeClassY) {
        this.typeClassT = typeClassT;
        this.typeClassY = typeClassY;
        modelMapper = new ModelMapper();
    }
	
	/* T là model DTO và Y là model Entity */
	
	public T toDTO(Y modelEntity) {
		T modelDto = modelMapper.map(modelEntity,typeClassT);
		return modelDto;
	}
	
	public Y toEntity(T modelDto) {
		Y modelEntity = modelMapper.map(modelDto,typeClassY);
		return modelEntity;
	}
	
	
    public <T> void merge(T source, T target) {
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().setSkipNullEnabled(true);
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	    modelMapper.map(source, target);
    }
}
