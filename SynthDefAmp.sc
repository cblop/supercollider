

// distorted sound
(

SynthDef(\Input, {|outBus=16|
    Out.ar(outBus, SoundIn.ar(0))
}).send(s);

SynthDef(\GxDist, {|inBus=16, outBus=17|
    Out.ar(outBus, LADSPA.ar(1, 4061, In.ar(inBus, 1), 11.3, 0, 0.5, 0, 20, 256, 5500, 0, 5500, 256, 0, 1, 1))
}).send(s);

SynthDef(\Gate, {|inBus=17, outBus=18|
    Out.ar(outBus, LADSPA.ar(1, 1845, 0.0139, In.ar(inBus, 1)))
}).send(s);

SynthDef(\Eq, {|inBus=18, outBus=19|
    Out.ar(outBus, LADSPA.ar(1, 1773, In.ar(inBus, 1), -19.6, -7.82, -1.12, -14.1, -3.48, 9.52, 9.17, -2.56, 2.36, 0))
}).send(s);

SynthDef(\Amp, {|inBus=19, outBus=20|
    Out.ar(outBus, LADSPA.ar(1, 2587, In.ar(inBus, 1), 2.5, -0.519, 0.461, 0.801, 60.4))
}).send(s);

SynthDef(\Compress, {|inBus=20, outBus=21|
    Out.ar(outBus, LADSPA.ar(1, 1772, In.ar(inBus, 1), 5.33, 1.04, 0.000977, 0.105, -1.5, 3.49))
}).send(s);

SynthDef(\Cabinet, {|inBus=21, outBus=22|
    Out.ar(outBus, LADSPA.ar(1, 2581, In.ar(inBus, 1), 5, 6.99))
}).send(s);

SynthDef(\Output, {|inBus=22|
    Out.ar(0, Pan2.ar(In.ar(inBus, 1), 0, 0.2))
}).send(s);

SynthDef(\Silence, {|outBus=60|
    Out.ar(outBus, DC.ar([0,0]))
}).send(s);

)


(
~out = Synth(\Output); 
~cab =Synth(\Cabinet); 
~comp = Synth(\Compress); 
~amp = Synth(\Amp); 
~eq = Synth(\Eq); 
~gate = Synth(\Gate); 
~dist = Synth(\GxDist);
~in = Synth(\Input);
)

// switch to clean
(
~in.set(\outBus, 18);
~dist.set(\inBus, 60);
~gate.set(\outBus, 61);
)

// switch to distorted
(
~in.set(\outBus, 16);
~dist.set(\inBus, 16);
~gate.set(\outBus, 18);
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
