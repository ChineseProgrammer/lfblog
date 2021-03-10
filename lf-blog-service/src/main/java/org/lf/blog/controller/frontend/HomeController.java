/* Copyright © 2021 LF. All rights reserved. */
package org.lf.blog.controller.frontend;

import org.lf.blog.bean.Article;
import org.lf.blog.utils.Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台首页数据查询
 *
 * @author LF
 * @version 1.0
 * @date 2021-03-09 10:44
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(value = "/article/list", method = RequestMethod.GET)
    public Map<String, Object> getArticleByState(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
//        int totalCount = articleService.getArticleCountByState(state, Util.getCurrentUser().getId(),keywords);
//        List<Article> articles = articleService.getArticleByState(state, page, count,keywords);
//        Map<String, Object> map = new HashMap<>();
//        map.put("totalCount", totalCount);
//        map.put("articles", articles);
        return null;
    }
}

