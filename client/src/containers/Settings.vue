<template>
    
     <div id="app">
	     <el-button type="text" @click="test">Test</el-button>
	      <h1>{{ msg }}</h1> 
     </div>
</template>

<script>
export default {
  name: 'Settings',
  data () {
    return {
      msg: 'Settings'
    }
  },
  methods: {
  	test(){
  		console.log("------------")
		 fetch('http://localhost:8082/users', {mode: 'cors'})  
		    .then((response) => {
		        if (response.ok) {
		            return response.json()
		        } else {
		            console.error('Error \r\nCode:' + response.status)
		        }
		    })
		    .then((data) => {
		        console.log(data)
				this.msg=data;
		    })
		    .catch((err)=> {
		        console.error(err)
		    })  	 
  		}
	}
};
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
