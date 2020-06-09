$(window).on('load', () => {
  var dropdown = $('.dropdown');
  dropdown.each((_, e) => {
    let button = $(e).children('.dropdown-trigger')
    button.click(() => {
      $(e).children('.dropdown-item').toggleClass('inactive')
      button.children('.fa-arrow-down, .fa-arrow-up')
        .toggleClass('fa-arrow-up')
        .toggleClass('fa-arrow-down')
    })
  })
  var dropdown = $('.dropdown-inline');
  dropdown.each((_, e) => {
    let button = $(e).children('.dropdown-inline-trigger')
    button.click(() => {
      $(e).children('.dropdown-inline-item').toggleClass('inactive')
      button.children('.fa-arrow-down, .fa-arrow-up')
        .toggleClass('fa-arrow-up')
        .toggleClass('fa-arrow-down')
    })
  })
})
