package com.epam.ems.service.impl;

import com.epam.ems.dao.TagDao;
import com.epam.ems.dao.entity.Tag;
import com.epam.ems.service.TagService;
import com.epam.ems.service.dto.TagDto;
import com.epam.ems.service.exception.DuplicateEntityException;
import com.epam.ems.service.exception.NoSuchEntityException;
import com.epam.ems.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan({"com.epam.ems.dao", "com.epam.ems.service"})
public class TagServiceImpl implements TagService {

    private TagDao dao;
    private Mapper<Tag, TagDto> mapper;

    @Autowired
    public TagServiceImpl(TagDao dao, Mapper<Tag, TagDto> mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public TagDto getById(Long id) throws NoSuchEntityException {
        return mapper.map(dao.retrieveById(id).orElseThrow(() -> new NoSuchEntityException(Tag.class)));
    }

    @Override
    public List<TagDto> getAll() {

        return dao.retrieveAll()
                .stream()
                .map(e->mapper.map(e))
                .collect(Collectors.toList());
    }

    @Override
    public void insert(TagDto entity) throws DuplicateEntityException {
        if(dao.findByName(entity.getName()).isPresent()){
            throw new DuplicateEntityException(entity.getId(),Tag.class);
        }
        dao.create(mapper.extract(entity));
    }

    @Override
    public void delete(Long id) throws NoSuchEntityException {
        dao.retrieveById(id).orElseThrow(()->new NoSuchEntityException(Tag.class));
        dao.delete(id);
    }

    @Override
    public void delete(TagDto entity) throws NoSuchEntityException {
        delete(entity.getId());
    }

    @Override
    public TagDto getByName(String name) {
        return mapper.map(dao.findByName(name).orElseThrow(() -> new NoSuchEntityException(Tag.class)));
    }
}
