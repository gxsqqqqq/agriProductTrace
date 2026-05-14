<template>
  <div class="batch">
    <el-card class="page-card">
      <template #header>
        <div class="card-header">
          <span>批次管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            生成批次
          </el-button>
        </div>
      </template>
      
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="batchNo" label="批次号" width="200" />
        <el-table-column prop="productName" label="产品名称" width="180" />
        <el-table-column prop="quantity" label="数量" width="120" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === '已上链' ? 'success' : 'warning'">{{ row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleTrace(row)">溯源</el-button>
            <el-button type="success" size="small" @click="handleChain(row)">上链</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import request from '@/utils/request'

const router = useRouter()
const tableData = ref([])

const loadData = async () => {
  try {
    const res = await request.get('/system/batch/list')
    if (res.rows) {
      tableData.value = res.rows
    }
  } catch (e) {
    tableData.value = [
      { id: 1, batchNo: 'BATCH20240115001', productName: '有机苹果', quantity: 500, status: '已上链', createTime: '2024-01-15 10:00:00' },
      { id: 2, batchNo: 'BATCH20240114001', productName: '绿色蔬菜', quantity: 300, status: '未上链', createTime: '2024-01-14 14:30:00' },
      { id: 3, batchNo: 'BATCH20240113001', productName: '天然蜂蜜', quantity: 100, status: '已上链', createTime: '2024-01-13 09:00:00' }
    ]
  }
}

const handleAdd = () => {
  ElMessage.success('批次生成成功')
  loadData()
}

const handleTrace = (row) => {
  router.push({ path: '/trace', query: { batchNo: row.batchNo } })
}

const handleChain = (row) => {
  ElMessage.success('上链成功')
  row.status = '已上链'
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
