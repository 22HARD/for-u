package com.loki.btmanage.mapper;

import com.loki.btmanage.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LOKI
 * @since 2022-02-20
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 批量更新菜单
     * @param rid
     * @param mids
     * @return
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
