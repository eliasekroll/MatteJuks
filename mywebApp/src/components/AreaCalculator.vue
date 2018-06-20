<template>
  <div>
    <h1 class="Yellow">{{title}}</h1>
    <span class="Yellow">Finn Areal</span>
    <br/>
    <span> Input 1 <input type="text" v-model=L1 :class="getInputClass(L1Valid)"></span>
    <br/>
    <span> Input 2 <input type="text" v-model=L2 :class="getInputClass(L2Valid)"></span>
    <br/>
    <br/>
    <span class="Yellow">Arealet er: {{Area}} (enheter) stort</span>
    <br/>
  </div>
</template>



<script>

  import axios from 'axios'

  export default {
    name: "DoubleInputAreaCalculator",
    props: {
      title: String,
    },

    data: function () {
      return {
        L1: 6,
        L1Valid: true,
        L2: 9,
        L2Valid: true,
        Area: 0,
      }

    },

    watch: {
      "L1": function () {
        this.L1Valid = this.isValidNumber(this.L1);
        if (!this.L1Valid)
          return;
        this.finnAreal()
      },

      "L2": function () {
        this.L2Valid = this.isValidNumber(this.L2);
        if (!this.L2Valid)
          return;
        this.finnAreal()
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
      updateInputValidFlags: function (largeValueValidKey, smallValueValidKey, largeValue, smallValue, callOnSuccess) {
        console.log("Called with", largeValueValidKey, smallValueValidKey, largeValue, smallValue)
        this[largeValueValidKey] = this.isValidNumber(largeValue);
        this[largeValueValidKey] = this[largeValueValidKey] && largeValue > smallValue;
        this[smallValueValidKey] = this.isValidNumber(smallValue)
        this[smallValueValidKey] = this[smallValueValidKey] && largeValue > smallValue;
        if (this[largeValueValidKey] && this[smallValueValidKey]){
          callOnSuccess()

        }
      },

      finnAreal: function () {
        let vue = this;

        axios
          .get("/api/areal/finnareal?L1=" + vue.L1 + "&L2=" + vue.L2)
          .then(function (results) {
            vue.Area = results.data;
          })
          .catch(function (error) {
            console.error("FEIL!!", JSON.stringify(error))
          })
        }
    },
      finnLengde: function () {
        let vue = this;

        axios
          .get("/api/areal/finnlengde?areal=" + vue.A + "&lengde=" + vue.lengthInput)
          .then(function (results) {
            vue.Lengde = results.data;
          })
          .catch(function (error) {
            console.error("FEIL", JSON.stringify(error))
          })
        }


  }

</script>

<style scoped>

  .Yellow {

    color: gold;
  }

</style>
