package org.lf.blog.controller;

import org.lf.blog.bean.RespBean;
import org.lf.blog.bean.Work;
import org.lf.blog.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LF on 2020/09/17.
 */
@RestController
@RequestMapping("/user/work")
public class WorkController {
    @Autowired
    WorkService workService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewArticle(Work work) {
        Integer result = workService.addNewWork(work);
        if (result == 1) {
            return new RespBean("success", work.getId() + "");
        } else {
            return new RespBean("error",  "作品保存失败!");
        }
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String,Object> getWorkList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count) {
        int totalCount = workService.getWorkCountByStateByUser();
        List<Work> works = workService.getWorkList(page, count);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("works", works);
        return map;
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable String ids) {
        boolean result = workService.deleteWorkByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
