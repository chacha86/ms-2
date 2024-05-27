export function test() {
    const filters = document.querySelectorAll(".filter");
    console.log(filters);
    filters.forEach((filter) => {
        console.log("filter");
        filter.addEventListener("click", (e) => {
            e.stopPropagation(); // 이벤트 전파를 중지하여 부모 요소로 전파되지 않도록 합니다
            const target = e.currentTarget as HTMLElement | null;
            if (target === null) {
                return;
            }

            const selectItem = target.parentNode as HTMLElement | null;
            if (selectItem === null) {
                return;
            }

            selectItem.classList.toggle("menu-dropdown-show");
            const subList = selectItem.nextElementSibling as HTMLElement | null;

            if (subList === null)
                return;

            subList.classList.toggle("menu-dropdown-show");
            console.log("dfsdfsdf");
        });
    });
}