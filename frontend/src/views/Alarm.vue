<template>
  <div class="alarm">
    <el-card class="page-card">
      <template #header>
        <div class="card-header">
          <span>报警管理</span>
        </div>
      </template>
      
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="alarmType" label="报警类型" width="150">
          <template #default="{ row }">
            <el-tag :type="row.level === '严重' ? 'danger' : row.level === '警告' ? 'warning' : 'info'">{{ row.alarmType }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="productName" label="关联产品" width="180" />
        <el-table-column prop="description" label="报警描述" show-overflow-tooltip />
        <el-table-column prop="alarmTime" label="报警时间" width="180" />
        <el-table-column prop="level" label="等级" width="100">
          <template #default="{ row }">
            <el-tag :type="row.level === '严重' ? 'danger' : row.level === '警告' ? 'warning' : 'info'">{{ row.level }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === '已处理' ? 'success' : 'danger'">{{ row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button v-if="row.status !== '已处理'" type="primary" size="small" @click="handleProcess(row)">处理</el-button>
            <el-button type="info" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const tableData = ref([])

const loadData = async () => {
  tableData.value = [
    { id: 1, alarmType: '温度异常', productName: '有机苹果', description: '仓储温度超过警戒线', alarmTime: '2024-01-15 10:00:00', level: '严重', status: '未处理' },
    { id: 2, alarmType: '库存不足', productName: '绿色蔬菜', description: '库存量低于安全库存', alarmTime: '2024-01-14 14:30:00', level: '警告', status: '未处理' },
    { id: 3, alarmType: '质量异常', productName: '天然蜂蜜', description: '质量检测参数异常', alarmTime: '2024-01-13 09:00:00', level: '一般', status: '已处理' }
  ]
}

const handleProcess = (row) => {
  row.status = '已处理'
  ElMessage.success('处理成功')
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
  font-size: 16px;
  font-weight: 600;
  color: #2e7d32;
}
</style>
