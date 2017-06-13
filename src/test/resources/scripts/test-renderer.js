//@ sourceUrl=src/test/resources/scripts/test-renderer.js

function render(fruit, request, $, _) {
  console.log(arguments.length);
  $('ul > li').repeat(fruit, function(type, li) {
    li.text(type);
  });
  return $.markup();
}