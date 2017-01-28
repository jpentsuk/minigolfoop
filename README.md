# minigolfoop

Licence:

MIT License
https://opensource.org/licenses/MIT

Copyright (c) [2017] [Minigolfoop by Jan Pentshuk]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



Mängujuhend:

Mängimiseks tuleb valida slaiderilt löögitugevus ja vajutada vastavale
suunanupule. Suundi on kokku 8 - põhi,lõuna,ida,lääs,kagu,kirre,edel,loe.
Nupud asuvad ilmakaarte-kujuliselt, mistõttu kasutamine on loogiline ja lihtne.
Keskel asuv nupp nimega "slaider" on mõeldud slaideri algasendisse liigutamiseks, 
kuigi seda võib ka hiirega algasendisse lohistada.

Mängu eesmärk on võimalikult väheste löökide arvuga saada pall
auku. Mängus on helesinine bassein, kuhu ei tohi palli lüüa. Kui 
pall upub, siis lähtestatakse antud leveli algasend.

Leveleid on kokku 2. Lööke loendatakse levelites eraldi.
Mängust väljumiseks võib keset mängu vajutada ristist akna kinni
või mängu lõppedes klikkida "Mängu on võidetud" tekstile.

Idee:

Tekkis idee luua lihtne minigolfimäng, kus on paar levelit ja eesmärk 
pall auku toimetada. Samuti lisada levelile erinevaid takistusi, nagu nt 
veesilm. Valida saaks löögitugevust ja -suunda.

Projekti edenemine:

Esialgu sai ehitatud projekti vaid arvutis, ilma githubi pushimata.
Esimestes kahes versioonis sai küll löögitugevust ja suunda valida,
aga kuna kasutatud oli esimesel juhul Timeline i ja teisel korral
TranslationTransitionit, siis need ei võimaldanud edasi liikuda.

Järgnevalt tegin projekti ümber AnimationTimerile, ehitasin 2 levelit
ja mängu sai juba kenasti mängida.

Probleemiks oli aga koodi liigne korduvus, mistõttu oli järgmiseks
eesmärgiks koodi võimalikult palju optimeerida. 

Kirjutasin kogu projekti algusest, kasutades juba olemasolevaid
koodijuppe, ent muutsin projekti struktuuri. Paigutasin koodi võimalikult
erinevatesse klassidesse ja meetoditesse, muutes selle lugemise ja veajälituse
kergemaks.

