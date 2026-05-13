<template>
  <div class="activity">
    <el-card class="page-card">
      <template #header>
        <div class="card-header">
          <span>营销活动</span>
          <el-button type="primary">
            <el-icon><Plus /></el-icon>
            创建活动
          </el-button>
        </div>
      </template>
      
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="活动名称" width="200" />
        <el-table-column prop="productName" label="关联产品" width="180" />
        <el-table-column prop="points" label="奖励积分" width="120" />
        <el-table-column prop="startTime" label="开始时间" width="180" />
        <el-table-column prop="endTime" label="结束时间" width="180" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === '进行中' ? 'success' : 'info'">{{ row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small">编辑</el-button>
            <el-button type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

const tableData = ref([])

const loadData = async () => {
  tableData.value = [
    { id: 1, name: '新年特惠', productName: '有机苹果', points: 100, startTime: '2024-01-01 00:00:00', endTime: '2024-02-01 00:00:00', status: '进行中' },
    { id: 2, name: '会员专享', productName: '天然蜂蜜', points: 200, startTime: '2024-01-10 00:00:00', endTime: '2024-01-20 00:00:00', status: '进行中' },
    { id: 3, name: '双11活动', productName: '绿色蔬菜', points: 50, startTime: '2023-11-01 00:00:00', endTime: '2023-11-15 00:00:00', status: '已结束' }
  ]
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.page-card {
  border-radius: 12px;
  border: 1px solid #c8e6c9;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: 600;
  color: #2e7d32;
}
</style>
