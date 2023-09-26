package edu.haue.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.haue.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    public void delete(long id);
}
