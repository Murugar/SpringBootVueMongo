<template>

<div class="container-fluid">

<div id="test" class="container-fluid">

  <div class="card">

  <div class="card-header bg-warning text-primary">CookBooks</div>
  
  <div class="card-block">

  <el-table :data="cookbooks" highlight-current-row  
        @current-change="handleCurrentChange"  
        class="table table-striped table-bordered success">
    <el-table-column type="index" width="50"> </el-table-column>
    <el-table-column property="name" label="name"> </el-table-column>
    <el-table-column property="price" label="price" ></el-table-column>
    <el-table-column property="buyNum" label="buynum"> </el-table-column>
    <el-table-column :context="_self" inline-template label="Add User Dishes">
      <div>
        <el-button size="small" @click="addUserDishes($index, row)" class="btn btn-primary">
          Add Dishes
        </el-button>
      </div>
    </el-table-column>
 </el-table>
 
 </div>
 </div>

 <br/>

   
  <div class="card">

  <div class="card-header bg-warning text-primary">Users</div>
  
  <div class="card-block"> 

  <el-table :data="userDishes" highlight-current-row   class="table table-striped table-bordered text-danger">
      <el-table-column type="index" width="50"> </el-table-column>
      <el-table-column property="userName" label="username"> </el-table-column>
      <el-table-column property="dishesName" label="dishesname" ></el-table-column>
      <el-table-column property="price" label="price"> </el-table-column>
      <el-table-column :context="_self" inline-template label="Delete">
        <div>
          <el-button size="small" @click="deleteDishes($index,row,userDishes)" class="btn btn-danger">
            Delete
          </el-button>
        </div>
      </el-table-column>
  </el-table>
 
 </div>
 </div>
 
  <br/>
  <br/>

 <!--<h1>总价格:{{msg}}
  </h1>-->
 </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        cookbooks: [],
        userDishes: [],
        msg: '0',
      }
    },

    computed: {
        allPrice:function () {
          var all=0;
          var data = this.userDishes;
          for(var o in data){  
              all += data[o].price;
          } 
          this.msg=all;
          return this.mag;
        }
    },
    mounted: function() {
        this.getCookbooks();
        this.getUserDishes();
    },
    methods: {
      handleCurrentChange(val) {
        this.currentRow = val;
        console.log(val)
        console.log(this.currentRow.address )
	      this.tableData2.push(val); 
      },

      getCookbooks(){
        console.log("to getCookbooks")
        fetch('http://localhost:8082/cookbooks', {mode: 'cors'})  
            .then((response) => {
                if (response.ok) {
                    return response.json()
                } else {
                    console.error('Error \r\nCode:' + response.status)
                }
            })
            .then((data) => {
                console.log(data)
                this.cookbooks=data;
                //this.msg=data;
            })
            .catch((err)=> {
                console.error(err)
            })  	 
      },

      getUserDishes(){
        console.log("to getUserDishes")
        fetch('http://localhost:8082/userDishes', {mode: 'cors'})  
            .then((response) => {
                if (response.ok) {
                    return response.json()
                } else {
                    console.error('Error \r\nCode:' + response.status)
                }
            })
            .then((data) => {
                console.log(data)
                this.userDishes=data;
            })
            .catch((err)=> {
                console.error(err)
            })  	 
      },


      addUserDishes(index,row) {
        var dishesName = row.name;
        console.log("----dishesName:",  );
        this.$prompt('Add User', 'Name', {
          confirmButtonText: 'Save',
          cancelButtonText: 'Cancel'
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: 'Added: ' + value
          });

          console.log("dishesName:",dishesName);
          console.log("userName:",value);
          fetch('http://localhost:8082/addDishes', {
              mode: 'cors',
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                dishesName:dishesName,
                userName: value,
              })
            })  
            .then((response) => {
                if (response.ok) {
                    return response.json()
                } else {
                    console.error('Error \r\nCode:' + response.status)
                }
            })
            .then((data) => {
                console.log('Success')
                console.log(data)
                this.userDishes.push(data); 
            })
            .catch((err)=> {
                console.error(err)
            })  
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Error'
          });       
        });
      },

      deleteDishes(index,row,userDishes){
          var userDishesId = row.id;
          console.log("id",userDishesId);
          fetch('http://localhost:8082/userDishes/'+userDishesId,{
               method: 'DELETE',
           })  
            .then((response) => {
                if (response.ok) {
                  console.log("Success")
                  userDishes.splice(index, 1);
                } else {
                    console.error('Error \r\nCode:' + response.status)
                }
            })
            .catch((err)=> {
                console.error(err)
            })  	
      },
    }
  }
</script>
