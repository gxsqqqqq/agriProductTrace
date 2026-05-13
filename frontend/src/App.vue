<template>
  <el-container class="app-container">
    <el-aside width="240px" class="aside">
      <div class="logo">
        <el-icon :size="32" color="#67c23a"><Link /></el-icon>
        <span class="logo-text">区块链溯源</span>
      </div>
      <el-menu
        :default-active="activeMenu"
        router
        background-color="#f0f9e8"
        text-color="#333"
        active-text-color="#67c23a"
      >
        <el-menu-item index="/dashboard">
          <el-icon><Odometer /></el-icon>
          <span>数据概览</span>
        </el-menu-item>
        <el-menu-item index="/products">
          <el-icon><Box /></el-icon>
          <span>产品管理</span>
        </el-menu-item>
        <el-menu-item index="/batch">
          <el-icon><Tickets /></el-icon>
          <span>批次管理</span>
        </el-menu-item>
        <el-menu-item index="/trace">
          <el-icon><Search /></el-icon>
          <span>溯源查询</span>
        </el-menu-item>
        <el-menu-item index="/activity">
          <el-icon><Present /></el-icon>
          <span>营销活动</span>
        </el-menu-item>
        <el-menu-item index="/welfare">
          <el-icon><GoldMedal /></el-icon>
          <span>福利管理</span>
        </el-menu-item>
        <el-menu-item index="/evaluate">
          <el-icon><ChatDotRound /></el-icon>
          <span>评价管理</span>
        </el-menu-item>
        <el-menu-item index="/alarm">
          <el-icon><Warning /></el-icon>
          <span>报警管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <div class="header-left">
          <span class="breadcrumb">首页</span>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-info">
              <el-avatar :size="36" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
              <span style="margin-left: 10px">管理员</span>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-main class="main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const activeMenu = computed(() => route.path)
</script>

<style scoped>
.app-container {
  height: 100vh;
}
.aside {
  background: linear-gradient(180deg, #f0f9e8 0%, #e8f5e9 100%);
  border-right: 1px solid #c8e6c9;
}
.logo {
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  padding: 0 20px;
}
.logo-text {
  font-size: 20px;
  font-weight: bold;
  color: #2e7d32;
}
.header {
  background: #ffffff;
  border-bottom: 1px solid #c8e6c9;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 24px;
}
.breadcrumb {
  font-size: 18px;
  color: #2e7d32;
  font-weight: 500;
}
.user-info {
  display: flex;
  align-items: center;
  cursor: pointer;
}
.main {
  padding: 24px;
  background: transparent;
}
</style>
