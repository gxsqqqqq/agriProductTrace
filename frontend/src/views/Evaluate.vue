<template>
  <div class="evaluate">
    <el-card class="page-card">
      <template #header>
        <div class="card-header">
          <span>评价管理</span>
        </div>
      </template>
      
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="userPhone" label="用户手机号" width="150" />
        <el-table-column prop="productName" label="产品名称" width="180" />
        <el-table-column prop="rating" label="评分" width="100">
          <template #default="{ row }">
            <el-rate v-model="row.rating" disabled :show-score="false" />
          </template>
        </el-table-column>
        <el-table-column prop="content" label="评价内容" show-overflow-tooltip />
        <el-table-column prop="createTime" label="评价时间" width="180" />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default>
            <el-button type="primary" size="small">回复</el-button>
            <el-button type="danger" size="small">删除</el-button>
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
    { id: 1, userPhone: '13800138001', productName: '有机苹果', rating: 5, content: '非常新鲜，口感很好！', createTime: '2024-01-15 10:00:00' },
    { id: 2, userPhone: '13800138002', productName: '绿色蔬菜', rating: 4, content: '蔬菜很新鲜，配送也很快', createTime: '2024-01-14 14:30:00' },
    { id: 3, userPhone: '13800138003', productName: '天然蜂蜜', rating: 5, content: '蜂蜜很纯正，味道很好', createTime: '2024-01-13 09:00:00' }
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
  font-size: 16px;
  font-weight: 600;
  color: #2e7d32;
}
</style>
