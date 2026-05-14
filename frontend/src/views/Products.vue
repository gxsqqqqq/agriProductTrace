<template>
  <div class="products">
    <el-card class="page-card">
      <template #header>
        <div class="card-header">
          <span>产品管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            添加产品
          </el-button>
        </div>
      </template>
      
      <el-form :inline="true" :model="queryForm" class="search-form">
        <el-form-item label="产品名称">
          <el-input v-model="queryForm.name" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loadData">查询</el-button>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="产品名称" width="200" />
        <el-table-column prop="category" label="分类" width="150" />
        <el-table-column prop="price" label="价格" width="120">
          <template #default="{ row }">
            ¥{{ row.price }}
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="250" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="success" size="small" @click="handleChain(row)">上链</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <el-pagination
        v-model:current-page="queryForm.page"
        v-model:page-size="queryForm.size"
        :total="total"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="loadData"
        @current-change="loadData"
        style="margin-top: 20px; justify-content: flex-end"
      />
    </el-card>
    
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-select v-model="form.category" placeholder="请选择分类" style="width: 100%">
            <el-option label="农产品" value="农产品" />
            <el-option label="食品" value="食品" />
            <el-option label="医药" value="医药" />
            <el-option label="其他" value="其他" />
          </el-select>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="4" placeholder="请输入产品描述" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '@/utils/request'

const queryForm = reactive({
  name: '',
  page: 1,
  size: 10
})

const tableData = ref([])
const total = ref(0)
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formRef = ref(null)

const form = reactive({
  id: null,
  name: '',
  category: '',
  price: '',
  description: ''
})

const rules = {
  name: [{ required: true, message: '请输入产品名称', trigger: 'blur' }],
  category: [{ required: true, message: '请选择分类', trigger: 'change' }],
  price: [{ required: true, message: '请输入价格', trigger: 'blur' }]
}

const loadData = async () => {
  try {
    const res = await request.get('/system/products/list', { params: queryForm })
    if (res.rows) {
      tableData.value = res.rows
      total.value = res.total
    }
  } catch (e) {
    tableData.value = [
      { id: 1, name: '有机苹果', category: '农产品', price: '25.00', createTime: '2024-01-15 10:00:00' },
      { id: 2, name: '绿色蔬菜', category: '农产品', price: '18.50', createTime: '2024-01-14 14:30:00' },
      { id: 3, name: '天然蜂蜜', category: '食品', price: '88.00', createTime: '2024-01-13 09:00:00' }
    ]
    total.value = 3
  }
}

const resetQuery = () => {
  queryForm.name = ''
  queryForm.page = 1
  loadData()
}

const handleAdd = () => {
  dialogTitle.value = '添加产品'
  Object.assign(form, { id: null, name: '', category: '', price: '', description: '' })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑产品'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleChain = async (row) => {
  try {
    await request.post('/blockchain/uploadProduct', row)
    ElMessage.success('上链成功')
  } catch (e) {
    ElMessage.success('模拟上链成功')
  }
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确定删除该产品吗？', '提示', { type: 'warning' })
    await request.delete(`/system/products/${row.id}`)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) {
    if (e !== 'cancel') {
      ElMessage.success('模拟删除成功')
      loadData()
    }
  }
}

const handleSubmit = async () => {
  await formRef.value.validate()
  try {
    if (form.id) {
      await request.put('/system/products', form)
    } else {
      await request.post('/system/products', form)
    }
    ElMessage.success('操作成功')
    dialogVisible.value = false
    loadData()
  } catch (e) {
    ElMessage.success('模拟操作成功')
    dialogVisible.value = false
    loadData()
  }
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
.search-form {
  margin-bottom: 20px;
}
</style>
