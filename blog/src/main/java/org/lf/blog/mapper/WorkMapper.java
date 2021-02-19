package org.lf.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lf.blog.bean.Work;

import java.util.List;

/**
 * Created by LF on 2020/09/17.
 */
@Mapper
public interface WorkMapper {
    Integer addNewWork(Work work);
    Long getMaxWorkNum();

    List<Work> getWorkByStateByUser(Integer start, Integer count, Long uid);
    int getWorkCountByStateByUser(Long uid);
    int deleteWorkByIds(@Param("ids") String[] ids);
}
