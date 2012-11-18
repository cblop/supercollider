
(

Ndef(\input, {
    SoundIn.ar(0);
}).add;

Ndef(\gxDist, {
    var in = \in.ar([0,0]);
    LADSPA.ar(1, 4061, in, 11.3, 0, 0.5, 0, 20, 256, 5500, 0, 5500, 256, 0, 1, 1);
}).add;

Ndef(\gate, {
    var in = \in.ar([0,0]);
    LADSPA.ar(1, 1845, 0.0139, in);
}).add;

Ndef(\eq, {
    var in = \in.ar([0,0]);
    LADSPA.ar(1, 1773, in, -19.6, -7.82, -1.12, -14.1, -3.48, 9.52, 9.17, -2.56, 2.36, 0);
}).add;

Ndef(\amp, {
    var in = \in.ar([0,0]);
    LADSPA.ar(1, 2587, in, 2.5, -0.519, 0.461, 0.801, 60.4);
}).add;

Ndef(\compress, {
    var in = \in.ar([0,0]);
    LADSPA.ar(1, 1772, in, 5.33, 1.04, 0.000977, 0.105, -1.5, 3.49)
}).add;

Ndef(\cabinet, {
    var in = \in.ar([0,0]);
    LADSPA.ar(1, 2581, in, 5, 6.99);
}).add;

Ndef(\output, {
    var in = \in.ar([0,0]);
    Pan2.ar(in, 0, 0.3);
}).add;

/*
Ndef(\silence, {
    Out.ar(outBus, DC.ar([0,0]))
}).add;
*/
)

Ndef(\output).play;
//change routing
Ndef(\input)<>>Ndef(\gxDist)<>>Ndef(\gate)<>>Ndef(\eq)<>>Ndef(\amp)<>>Ndef(\compress)<>>Ndef(\cabinet)<>>Ndef(\output);

Ndef(\input)<>>Ndef(\eq)<>>Ndef(\amp)<>>Ndef(\compress)<>>Ndef(\cabinet)<>>Ndef(\output);

    //  guitarix_distortion
    // in
    // (out)
    // overdrive 1:20, 10.5
    // overdrive 0/1, 0
    // drive 0:1, 0.5
    // drivelevel 0:1, 0
    // drivegain -20:20, 0
    // highpass 8:1000, 256
    // lowpass 1000:10000, 5500
    // lowhighpass 0/1, 1
    // highcut 1000:10000, 5500
    // lowcut 8:1000, 256
    // lowhighcut 0/1, 1
    // trigger 0:1, 1
    // vibrato 0.01:1, 1



        // hard_gate
        // threshold 0:1


    // C* eq (all -48:30)
    // 31Hz
    // 63Hz
    // 125Hz
    // 250Hz
    // 500Hz
    // 1kHz
    // 2kHz
    // 4kHz
    // 8kHz
    // 16kHz


    // ampV
    // in
    // gain 0:3
    // bass -9:9
    // tone 0:1
    // drive 0.0001:1
    // watts 5:150


    // c* compress
    // gain 0:24
    // ratio 1:10
    // attack 0.000977:1
    // release 0.000977:1
    // threshold -30:400
    // knee radius 1:10


    // c* cabinetII
    // model 0/7
    // gain -24:24
