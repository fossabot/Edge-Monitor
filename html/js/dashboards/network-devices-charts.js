// All this JS is for creating charts
// $mm is just a lightweight jQuery substitution library I made
// $mm should be mostly swappable for normal jQuery

let colors = {
  background: $mm.cssVar('background'),
  primary: $mm.cssVar('primary'),
  secondary: $mm.cssVar('secondary'),
  error: $mm.cssVar('error'),
}

// Charts
$mm.fn.extend({
  sparkline: function(attrData) {
    var data = attrData.data ? attrData.data : {},
        options = attrData.options ? attrData.options : {};

    data.datasets = data.datasets.map(t => {
      return $mm.merge({
        fill: true,
        backgroundColor: colors.primary + '18',
        borderColor: colors.primary,
        pointBorderColor: '#fff',
        lineTension: 0.25,
        pointRadius: 0
      }, t)
    })

    let max = data.datasets.reduce((a, b) => Math.max(a, Math.max(...b.data)), 0)
    
    options = $mm.merge(true, {
      maintainAspectRatio: false,
      legend: {
        display: false
      },
      scales: {
        xAxes: [{ display: false }],
        yAxes: [{ display: false, ticks: {min: 0, max: max + 5} }]
      },
      tooltips: {
        enabled: false
      }
    }, options)

    return new Chart(this, {
      type: 'line',
      data: data,
      options: options
    })
  }
})

// Utility
$mm.extend({
  randData: (n, max, min = 0) => Array.from({length: n}, () => $mm.randInt(max, min))
})

function formatDate (date) {
  return date.toLocaleDateString('en-us')
}

let dateInputs = $mm.findAll('#date-range input')

let datePickers = [
  new Pikaday({
    field: dateInputs[0],
    toString: formatDate,
    theme: 'dark-theme'
  }),
  new Pikaday({
    field: dateInputs[1],
    toString: formatDate,
    theme: 'dark-theme'
  })
]

let date = new Date()
date.setDate(new Date().getDate() - 7)
datePickers[0].setDate(date)
datePickers[1].setDate(new Date())

let labels = Array.from({length: 10}, (v, i) => String.fromCharCode(97 + i))

$mm('#devices-online').sparkline({
  data: {
    labels: labels,
    datasets: [{
      data: [15,15,10,20,18,35,20,5,9,15]
    }]
  }
})

$mm('#devices-offline').sparkline({
  data: {
    labels: labels,
    datasets: [{
      data: $mm.randData(10, 100, 0)
    }]
  }
})

$mm('#timeouts').sparkline({
  data: {
    labels: labels,
    datasets: [{
      data: $mm.randData(10, 100, 0)
    }]
  }
})

$mm('#menu').on('click', e => {
  $mm('#sidebar nav').toggleClass('open')
})