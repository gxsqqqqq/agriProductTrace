package com.trace.mapper;

import java.util.List;
import com.trace.entity.SysUserPost;

public interface SysUserPostMapper
{
    public int deleteUserPostByUserId(Long userId);

    public int countUserPostById(Long postId);

    public int deleteUserPost(Long[] ids);

    public int batchUserPost(List<SysUserPost> userPostList);
}
