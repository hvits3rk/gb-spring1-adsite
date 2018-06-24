const locale = {
    ru: {
        edit: "Редактировать"
    },
    en: {
        edit: "Edit"
    }
};

function getCookie(name) {
    let value = "; " + document.cookie;
    let parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();
}

function renderAds(adList) {
    const appendBeacon = document.querySelector("#append_beacon");
    let msgEditLocalized = locale.en.edit;
    if (getCookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE") === "ru") {
        msgEditLocalized = locale.ru.edit;
    }

    for (let {id, name, publishedDate: date, content, phoneNumber, company, category} of adList) {
        let elem = document.createElement("template");
        let template =
            `<div class="card">
                <div class="card-body">
                    <div class="clearfix">
                        <h5 class="card-title mb-0 float-left">${name}</h5>
                        <a href="edit/${id}" class="float-right">${msgEditLocalized}</a>
                    </div>
                    <p class="mb-2 font-weight-light text-muted">${id}</p>
                    <p class="font-weight-light text-muted">${date}</p>
                    <p class="card-text">${content}</p>
                    <p class="text-muted">${phoneNumber}</p>
                    <a href="/company/detail/${company.id}" class="card-link">#${company.name}</a>
                    <a href="/category/detail/${category.id}" class="card-link">#${category.name}</a>
                </div>
            </div>`;
        template = template.trim();
        elem.innerHTML = template;

        appendBeacon.appendChild(elem.content.firstChild);
    }
}

function getDocumentHeight() {
    const body = document.body;
    const html = document.documentElement;

    return Math.max(
        body.scrollHeight, body.offsetHeight, html.clientHeight
    );
}

function getScrollTop() {
    return (window.pageYOffset !== undefined) ? window.pageYOffset :
        (document.documentElement || document.body.parentNode || document.body).scrollTop;
}

function debounce(callback, wait, context = this) {
    let timeout = null;
    let callbackArgs = null;

    const later = () => callback.apply(context, callbackArgs);

    return function () {
        callbackArgs = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait)
    }
}

// first page already rendered
let pageCounter = 1;

const fetchRequest = debounce(() => {
    fetch(`${location.protocol}//${location.host}${location.pathname}/pagination?page=${pageCounter}`)
        .then(resp => resp.json())
        .then(({adList}) => {
            if (adList.length !== 0) {
                renderAds(adList);
                pageCounter++;
            } else {
                document.removeEventListener("scroll", scrollFetch, false);
            }
        });
}, 200);

const scrollFetch = () => {
    if (getScrollTop() < getDocumentHeight() - window.innerHeight) return;
    fetchRequest();
};

document.addEventListener("scroll", scrollFetch);
