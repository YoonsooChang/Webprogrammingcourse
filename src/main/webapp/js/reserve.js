document.addEventListener("DOMContentLoaded", () => reqHandler.getRequest());

let ticketCountArr = [];
let reservationPrices = [];

const appendReservation = (data) => {


	//const {
	//	productImage,
	//  productPrices,
	//} = data;
	//<1> 이미지 세팅
	//setUpImage(productImage);

	//<2> 전시 정보 세팅
	//fillUpDetailSectionNodes();

	//<3>form UI 동작 세팅
	//setEventsOnForm();


}
/*

const CountController = () => this.registerEvents();

CountController.prototype = {
	registerEvents: () => {
		const controllers = document.querySelectorAll(".count_control");
		
		controllers.forEach((controller) => {
			this.child
			this.
		})
	}
}


const setUpFormActions = () => {

}
*/

const urlGetParams = new URL(window.location.href).searchParams;
const reqHandler = new RequestHandler(`api/display/reservation/${urlGetParams.get("id")}`,
	appendReservation,
	() => console.log('error'),
	() => true)