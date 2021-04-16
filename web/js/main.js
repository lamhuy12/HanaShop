let rangeMin = document.querySelector('.rangeMin');
let rangeMax = document.querySelector('.rangeMax');

let showRangeMin = document.querySelector('.showRangeMin');
let showRangeMax = document.querySelector('.showRangeMax');

function changeValueMin() {
	rangeMin.value = Math.min((rangeMin.value - rangeMin.min), (rangeMax.value - rangeMin.min) - 1);
	showRangeMin.textContent = rangeMin.value;
}

function changeValueMax() {
	rangeMax.value = Math.max((rangeMin.value - rangeMax.min) + 1, (rangeMax.value - rangeMax.min));
	showRangeMax.textContent = rangeMax.value;
}

changeValueMin();
changeValueMax();

rangeMin.addEventListener('input', changeValueMin);
rangeMax.addEventListener('input', changeValueMax);