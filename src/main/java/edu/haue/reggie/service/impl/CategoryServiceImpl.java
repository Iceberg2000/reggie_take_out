package edu.haue.reggie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.haue.reggie.entity.Category;
import edu.haue.reggie.mapper.CategoryMapper;
import edu.haue.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
