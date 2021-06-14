<template>
  <a-modal
    :title="model && model.id?'修改':'新增'"
    :width="640"
    :visible="visible"
    :confirmLoading="loading"
    @ok="() => { $emit('ok') }"
    @cancel="() => { $emit('cancel') }"
  >
    <a-spin :spinning="loading">
      <a-form-model
        ref="form"
        :model="model"
        :rules="rules"
        v-bind="formLayout">
        <a-form-model-item v-show="model && model.id" label="ID">
          <a-input :disabled="model && model.id" v-model="model.id"/>
        </a-form-model-item>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <a-form-model-item label="${field.comment}" prop="${field.propertyName}">
          <a-input v-model="model.${field.propertyName}" placeholder="请输入${field.comment}" />
        </a-form-model-item>
</#if>
</#list>
      </a-form-model>
    </a-spin>
  </a-modal>
</template>

<script>
export default {
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    loading: {
      type: Boolean,
      default: () => false
    },
    model: {
      type: Object,
      default: () => null
    }
  },
  data () {
    this.formLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 13 }
      }
    }
    return {
      form: {},
      rules: {
    <#list table.fields as field>
    <#if !field.keyFlag><#--生成普通字段 -->
        ${field.propertyName}: [{ required: true, message: '请输入${field.comment}', trigger: 'change' }]<#if field_has_next>,</#if>
    </#if>
    </#list>
      }
    }
  },
  created () {
    console.log('custom modal created')
  }
}
</script>
