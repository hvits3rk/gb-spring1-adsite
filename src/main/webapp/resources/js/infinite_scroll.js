function renderAds(adList) {
    adList.forEach(ad => {
        const appendBeacon = document.querySelector("#append_beacon");
        let elem = document.createElement("template");
        let template =
            `<div class="card" style="width: 38rem; margin: 12px;">
                <div class="card-body">
                    <div class="clearfix">
                        <h5 class="card-title mb-0 float-left">${ad["name"]}</h5>
                        <a href="edit/${ad['id']}" class="float-right">Edit</a>
                    </div>
                    <p class="mb-2 font-weight-light text-muted">${ad["id"]}</p>
                    <p class="font-weight-light text-muted">Published: ${ad["publishedDate"]}</p>
                    <p class="card-text">${ad["content"]}</p>
                    <p class="text-muted">${ad["phoneNumber"]}</p>
                    <a href="/company/${ad['company']['id']}" class="card-link">#${ad["company"]["name"]}</a>
                    <a href="/category/${ad['category']['id']}" class="card-link">#${ad["category"]["name"]}</a>
                </div>
            </div>`;
        template = template.trim();
        elem.innerHTML = template;

        appendBeacon.appendChild(elem.content.firstChild);
    });
}

function getDocumentHeight() {
    const body = document.body;
    const html = document.documentElement;

    return Math.max(
        body.scrollHeight, body.offsetHeight,
        html.clientHeight, html.scrollHeight, html.offsetHeight
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

let pageCounter = 0;

const fetchRequest = debounce(() => {
    fetch(`${document.location.href}/ajax?page=${pageCounter}&items=8`)
        .then(resp => resp.json())
        .then(({ adList }) => {
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

function ready(func) {
    if (document.attachEvent ? document.readyState === "complete" : document.readyState !== "loading") {
        func();
    } else {
        document.addEventListener('DOMContentLoaded', func);
    }
}

ready(fetchRequest);

document.addEventListener("scroll", scrollFetch);
