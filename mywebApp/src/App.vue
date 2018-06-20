<template>

  <div id="app">

    <div class="TopBar">


      <p class=Title>MatteJuks</p>


    </div>

    <h1 class="Blue">Pytagoras:</h1>

    <span class="Blue">Finn Hypotenus:</span>
    <br/>


    Katet 1 <input type="text" v-model="k1" :class="getInputClass(k1valid)">
    <br/>
    Katet 2 <input type="text" v-model="k2" :class="getInputClass(k2valid)">
    <br/>
    <br/>
    <span class="Blue">Hypotenusen er: {{h}} (enheter) lang</span>
    <br/>
    <br/>


    <span class="Green">Finn Katet:</span>
    <br/>
    Hypotenus <input type="text" v-model="hyp" :class="getInputClass(hypValid)">
    <br/>
    Katet <input type="text" v-model="kat1" :class="getInputClass(kat1Valid)">
    <br/>
    <br/>
    <br/>
    <span class="Green">Kateten er: {{kat2}} (enheter) lang</span>
    <br/>
    <br/>

    <DoubleInputAreaCalculator title="Areal kalkulator komponent 1"/>

   <!--
    <span class="Orange">Finn Lengde:</span>
    <br/>
    Totalt Areal <input type="text" v-model="A" :class="getInputClass(AValid)">
    <br/>
    Lengde <input type="text" v-model="L" :class="getInputClass(LValid)">
    <br/>
    <br/>
    <br/>
    <span class="Orange">Lengde 2 er: {{Lengde}} (enheter) lang</span>
    <br/>
    <br/>
  -->

  </div>
</template>

<script>
  import axios from 'axios'
  import DoubleInputAreaCalculator from "./components/AreaCalculator"

  export default {
    name: 'App',
    components: {DoubleInputAreaCalculator},
    data: function () {
      return {
        k1: 1,
        k1valid: true,
        k2: 1,
        k2valid: true,
        h: 0,
        kat1: 5,
        kat1Valid: true,
        hyp: 9,
        hypValid: true,
        kat2: 0,
      }
    },

    watch: {
      "k1": function () {
        this.k1valid = this.isValidNumber(this.k1)
        if (!this.k1valid)
          return;
        this.finnHyp()
      },

      "k2": function () {
        this.k2valid = this.isValidNumber(this.k2)
        if (!this.k2valid)
          return;
        this.finnHyp()
      },
      "hyp": function () {
        this.updateInputValidFlags("hypValid", "kat1Valid", this.hyp, this.kat1, this.finnKat)
      },
      "kat1": function () {
        this.updateInputValidFlags("hypValid", "kat1Valid", this.hyp, this.kat1, this.finnKat)
      },
      "L1": function () {
        this.L1Valid = this.isValidNumber(this.L1)
        if (!this.L1Valid)
          return;
        this.finnAreal()
      },

      "L2": function () {
        this.L2Valid = this.isValidNumber(this.L2)
        if (!this.L2Valid)
          return;
        this.finnAreal()
      },


      "A": function () {
        this.updateInputValidFlags("AValid", "lengthValid", this.A, this.lengthInput, this.finnLengde)
      },
      "lengthInput": function () {
        this.updateInputValidFlags("AValid", "lengthValid", this.A, this.lengthInput, this.finnLengde)
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
        if (this[largeValueValidKey] && this[smallValueValidKey]) {
          callOnSuccess()

        }
      },
      finnHyp: function () {
        let vue = this;

        axios
          .get("/api/pytagoras/finnhypotenus?k1=" + vue.k1 + "&k2=" + vue.k2)
          .then(function (results) {
            vue.h = results.data;
          })

      },

      finnKat: function () {
        let vue = this;

        axios
          .get("/api/pytagoras/finnk atet?kat1=" + vue.kat1 + "&hyp=" + vue.hyp)
          .then(function (results) {
            vue.kat2 = results.data;
          })

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
      },
    }
  }

</script>


<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 220px;
  }

  .Blue {

    color: aqua;
  }

  .Green {

    color: chartreuse;
  }

  .Yellow {

    color: gold;
  }

  .Orange {

    color: darkorange;
  }

  .TopBar {

    position: fixed;
    width: 100%;
    height: 3%;
    top: -5px;
    padding: 60px 0px 60px 0px;
    background-color: #41423f;
    box-shadow: 2px 2px 110px #393a37;
    color: #ffbf00;
    z-index: 10;
  }

  .input-red {
    background-color: #fa2d43;
  }

  .Title {

    font-size: 70px;
    display: inline;
    color: darkorange;
    transform: translateY(10px);
  }
</style>
