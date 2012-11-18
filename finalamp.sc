
p = ProxySpace.push(s);
// distorted sound
(


~input = {SoundIn.ar(0)};

~gxDist = {
    var in = \in.ar(0);
    LADSPA.ar(1, 4061, in, 11.3, 0, 0.5, 0, 20, 256, 5500, 0, 5500, 256, 0, 1, 1);
};

~gate = {
    var in = \in.ar(0);
    LADSPA.ar(1, 1845, 0.0139, in);
};

~eq = {
    | b1 = -19.6, b2 = -7.82, b3 = -1.12, b4 = -14.1, b5 = -3.48, b6 = 9.52, b7 = 9.17, b8 = -2.56, b9 = 0, b10 = 0 |
    var in = \in.ar(0);
    LADSPA.ar(1, 1773, in, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
};

~amp = {
    | gain=2.5 |
    var in = \in.ar(0);
    LADSPA.ar(1, 2587, in, gain, -0.519, 0.461, 0.801, 60.4);
};

~compress = {
    var in = \in.ar(0);
    LADSPA.ar(1, 1772, in, 5.33, 1.04, 0.000977, 0.105, -1.5, 3.49);
};

~cabinet = {
    | gain = 5 |
    var in = \in.ar(0);
    LADSPA.ar(1, 2581, in, 5, gain);
};

~output = {
    var in = \in.ar(0);
    Pan2.ar(in, 0, 0.1);
};

~output.play;

)


~input<>>~gxDist<>>~gate<>>~eq<>>~amp<>>~compress<>>~cabinet<>>~output;

~input<>>~eq<>>~amp<>>~compress<>>~cabinet<>>~output;


// consider using Pbind on these

// make it clean
(
~eq.set(\b1, -16);
~eq.set(\b2, -10);
~eq.set(\b3, 0);
~eq.set(\b4, -5);
~eq.set(\b5, -10);
~eq.set(\b6, -10);
~eq.set(\b7, -3);
~eq.set(\b8, -1);
~eq.set(\b9, -4);
~eq.set(\b10, -4);

~amp.set(\gain, 2.5);
~cabinet.set(\gain, 10);
~input<>>~eq<>>~amp<>>~cabinet<>>~output;
)

// make it dirty
(
~eq.set(\b1, -19.6);
~eq.set(\b2, -7.82);
~eq.set(\b3, -1.12);
~eq.set(\b4, -14.1);
~eq.set(\b5, -3.48);
~eq.set(\b6, 9.52);
~eq.set(\b7, 9.17);
~eq.set(\b8, -2.56);
~eq.set(\b9, 2.36);
~eq.set(\b10, 0);

~amp.set(\gain, 2.5);
~cabinet.set(\gain, 5);
~input<>>~gxDist<>>~gate<>>~eq<>>~amp<>>~compress<>>~cabinet<>>~output;
)
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
