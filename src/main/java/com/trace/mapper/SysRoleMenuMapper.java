package com.trace.mapper;

import java.util.List;
import com.trace.entity.SysRoleMenu;

public interface SysRoleMenuMapper
{
    public int checkMenuExistRole(Long menuId);

    public int deleteRoleMenuByRoleId(Long roleId);

    public int deleteRoleMenu(Long[] ids);

    public int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
