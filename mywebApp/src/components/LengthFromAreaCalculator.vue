<template>

     <h1 class="Orange">{{title}}</h1>
     <span class="Orange">Finn Lengde:</span>
  <br/>
      Totalt Areal <input type="text" v-model="inputArea" :class="getInputClass(inputAreaValid)">
  <br/>
      Lengde <input type="text" v-model="inputLength" :class="getInputClass(inputLengthValid)">
  <br/>
  <br/>
  <br/>
      <span class="Orange">Lengde 2 er: {{resultLength}} (enheter) lang</span>
  <br/>

  <br/>
</template>

<script>

    import axios from 'axios'

    export default {
        name: "LengthFromAreaCalculator",
        props: {
          title: String,
        },

      data: function () {
          return {
            inputArea: 4,
            inputAreaValid: true,
            inputLength: 2,
            inputLengthValid: true,
            resultLength: 2,
          }
      },

      watch: {
        "inputArea": function () {
          if (this.inputAreaValid = this.isValidNumber(this.inputArea))
            if (!this.inputAreaValid)
              return;
            this.findLength()

        },

        "inputLength": function () {
          if (this.inputLengthValid = this.isValidNumber(this.inputLength))
            if (!this.inputLengthValid)
              return;
            this.findLength()
        },
      },

      methods: {

        isValidNumber: function (value) {
          return (value > 0 && typeof parseFloat(value) === "number")
        },

        getInputClass: function (valid) {
          if (!valid)
            return "input-red";
          else return "";
      },

      findLength: function () {

          let vue = this

          axios
            .get ("/api/areal/finnlengde?areal="+vue.inputArea+"lengde"+vue.inputLength)
            .then (function (results) {
              console.log(results);
              vue.resultLength = results.data;
            })


        }
      }
    },
</script>

<style scoped>

</style>
