<template>
  <div class="welfare">
    <el-card class="page-card">
      <template #header>
        <div class="card-header">
          <span>福利管理</span>
          <el-button type="primary">
            <el-icon><Plus /></el-icon>
            发放福利
          </el-button>
        </div>
      </template>
      
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="userPhone" label="用户手机号" width="150" />
        <el-table-column prop="welfareName" label="福利名称" width="200" />
        <el-table-column prop="points" label="积分数量" width="120" />
        <el-table-column prop="grantTime" label="发放时间" width="180" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag type="success">{{ row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template #default>
            <el-button type="info" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const tableData = ref([])

const loadData = async () => {
  tableData.value = [
    { id: 1, userPhone: '13800138001', welfareName: '新用户注册福利', points: 500, grantTime: '2024-01-15 10:00:00', status: '已领取' },
    { id: 2, userPhone: '13800138002', welfareName: '每日签到', points: 10, grantTime: '2024-01-15 09:00:00', status: '已领取' },
    { id: 3, userPhone: '13800138003', welfareName: '节日福利', points: 200, grantTime: '2024-01-14 14:00:00', status: '已领取' }
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
