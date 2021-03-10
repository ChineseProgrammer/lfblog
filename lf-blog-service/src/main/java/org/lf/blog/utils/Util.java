package org.lf.blog.utils;

import org.lf.blog.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by LF on 2020/09/17.
 */
public class Util {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
